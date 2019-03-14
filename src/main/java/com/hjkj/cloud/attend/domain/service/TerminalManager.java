package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.domain.repository.IDepartmentRepository;
import com.hjkj.cloud.attend.domain.repository.ITerminalRepository;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl.AbstractBatchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TerminalManager extends AbstractBatchRepositoryImpl<Terminal> {

    @Autowired
    private ITerminalRepository terminalRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;

    public void saveTerminalInfo(Terminal terminal,String departId) {
        if (StringUtils.isEmpty(terminal.getId())) {
            terminal.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        if (StringUtils.isEmpty(departId)) {
            Optional<Department> department = departmentRepository.findDepartmentByLevel(1);
            department.ifPresent(terminal::setDepartment);
        } else {
            Department department = findDepartById(departId);
            terminal.setDepartment(department);
        }
        terminalRepository.saveAndFlush(terminal);
    }

    public void saveTerminalList(List<Terminal> terminalList) {
        terminalRepository.saveAll(terminalList);
    }


    @Transactional
    public void deleteTerminalById(String tmId) {
        // XXX: 关联表是由用户表维护的，删除终端不会级联删除关联表中该终端的数据
        terminalRepository.deleteUsersByTerminalId(tmId);
        terminalRepository.deleteById(tmId);
    }

    @Transactional
    public void deleteTerminalBatch(@NotNull List<String> tmIds) {
        terminalRepository.deleteUsersByTerminalIds(tmIds);
        terminalRepository.deleteTerminalBatch(tmIds);
    }


    @Transactional
    public void updateTerminal(Terminal terminal) {
        Terminal oldTerminal = findTerminalById(terminal.getId());
        if (!StringUtils.isEmpty(terminal.getMacAddr())) {
            oldTerminal.setMacAddr(terminal.getMacAddr());
        }
        if (!StringUtils.isEmpty(terminal.getDepartment())) {
            oldTerminal.setDepartment(terminal.getDepartment());
        }
        if (!StringUtils.isEmpty(terminal.getName())) {
            oldTerminal.setName(terminal.getName());
        }
        if (!StringUtils.isEmpty(terminal.getPassword())) {
            oldTerminal.setPassword(terminal.getPassword());
        }
        if (!StringUtils.isEmpty(terminal.getTmIp())) {
            oldTerminal.setTmIp(terminal.getTmIp());
        }
        if (!StringUtils.isEmpty(terminal.getVersion())) {
            oldTerminal.setVersion(terminal.getVersion());
        }

        oldTerminal.setTransmitState(terminal.getTransmitState());
        oldTerminal.setAcceptState(terminal.getAcceptState());
        oldTerminal.setTmUsableCap(terminal.getTmUsableCap());
        oldTerminal.setTmRegnum(terminal.getTmRegnum());
        oldTerminal.setTmTotalCap(terminal.getTmTotalCap());
        oldTerminal.setTmStrangerRecords(terminal.getTmStrangerRecords());
        oldTerminal.setTmPort(terminal.getTmPort());
        oldTerminal.setTmCompRecords(terminal.getTmCompRecords());
        oldTerminal.setTmAllowNum(terminal.getTmAllowNum());
        oldTerminal.setOnlineState(terminal.getOnlineState());

        terminalRepository.saveAndFlush(oldTerminal);

    }

    public Page<Terminal> findTerminalCriteria(Integer page, Integer size, Terminal tmQuery, List<String> departIds) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "tmRegTime");
        if (tmQuery == null) {
            return terminalRepository.findAll(pageable);
        }
        return terminalRepository.findAll((Specification<Terminal>) (Root<Terminal> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != tmQuery.getName() && !"".equals(tmQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + tmQuery.getName() + "%"));
            }
            if (-1 != tmQuery.getOnlineState()) {
                list.add(cb.equal(root.<Integer>get("onlineState"), tmQuery.getOnlineState()));
            }
            if (null != departIds && departIds.size() > 0) {
                list.add(root.<Department>get("department").<String>get("id").in(departIds));
            }

            Predicate[] p = new Predicate[list.size()];

            return cb.and(list.toArray(p));
        },pageable);
    }

    public List<Terminal> findTerminalsByAcceptOn() {
        return terminalRepository.findByAcceptState(Constant.STATE_ON);
    }

    public Terminal findTerminalByMacAddr(String macAddr) {
        Optional<Terminal> terminal = terminalRepository.findByMacAddr(macAddr);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + macAddr + "]不存在");
        }
        return terminal.get();
    }

    public Terminal findTerminalById(String tmId) {
        Optional<Terminal> terminal = terminalRepository.findById(tmId);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + tmId + "]不存在");
        }
        return terminal.get();
    }

    private Department findDepartById(String departId) {
        Optional<Department> department = departmentRepository.findById(departId);
        return department.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到部门[" + departId + "]"));
    }

}
