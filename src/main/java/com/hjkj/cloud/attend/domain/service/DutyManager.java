package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.domain.model.Role;
import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.IDutyRepository;
import com.hjkj.cloud.attend.domain.repository.ITerminalRepository;
import com.hjkj.cloud.attend.domain.repository.IUserRepository;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DutyManager {

    @Autowired
    private IDutyRepository dutyRepository;
    @Autowired
    private ITerminalRepository terminalRepository;
    @Autowired
    private IUserRepository userRepository;

    public void saveDutyLog(Duty duty,String userId,String macAddr) {
        if (StringUtils.isEmpty(duty.getId())) {
            duty.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        User user = findUserById(userId);
        duty.setUser(user);
        Terminal terminal = findTerminalByMacAddr(macAddr);
        duty.setTerminal(terminal);
        dutyRepository.saveAndFlush(duty);
    }

    public Page<Duty> findDutyCriteria(Integer page, Integer size, Duty dutyQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        if (dutyQuery == null) {
            return dutyRepository.findAll(pageable);
        }
        return dutyRepository.findAll((Specification<Duty>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != dutyQuery.getId() && !"".equals(dutyQuery.getId())){
                list.add(cb.equal(root.get("id").as(String.class), dutyQuery.getId()));
            }
            if(null != dutyQuery.getStartDate()){
                list.add(cb.greaterThanOrEqualTo(root.get("date").as(String.class), Constant.SIMPLE_DATE_FORMAT.format(dutyQuery.getStartDate())));
            }
            if (null != dutyQuery.getEndDate()) {
                list.add(cb.lessThanOrEqualTo(root.get("date").as(String.class), Constant.SIMPLE_DATE_FORMAT.format(dutyQuery.getEndDate())));
            }
            if (null != dutyQuery.getUser()) {
                if (null != dutyQuery.getUser().getCardNum() && !"".equals(dutyQuery.getUser().getCardNum())) {
                    list.add(cb.like(root.<User>get("user").<String>get("cardNum"), dutyQuery.getUser().getCardNum() + "%"));
                }
            }
            if (null != dutyQuery.getTerminal()) {
                if (null != dutyQuery.getTerminal().getName() && "".equals(dutyQuery.getTerminal().getName())) {
                    list.add(cb.like(root.<Terminal>get("terminal").<String>get("name"), "%" + dutyQuery.getTerminal().getName() + "%"));
                }
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        },pageable);
    }

    private Terminal findTerminalByMacAddr(String macAddr) {
        Optional<Terminal> terminal = terminalRepository.findByMacAddr(macAddr);
        if (!terminal.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"终端[" + macAddr + "]不存在");
        }
        return terminal.get();
    }

    private User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到用户[" + userId + "]"));
    }

}
