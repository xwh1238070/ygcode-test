package com.ygsoft.user.management.service.query.impl;

import com.ygsoft.user.management.dao.UserDao;
import com.ygsoft.user.management.domain.po.UserPO;
import com.ygsoft.user.management.domain.transfer.UserTransfer;
import com.ygsoft.user.management.domain.vo.UserQueryVO;
import com.ygsoft.user.management.domain.vo.UserVO;
import com.ygsoft.user.management.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户查询服务实现类
 * 
 * 实现用户数据查询逻辑
 * 
 * @author developer
 * @date 2024-12-15
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    
    @Autowired
    private UserDao userDao;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public UserVO getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        
        UserPO userPO = userDao.findById(userId).orElse(null);
        
        if (userPO == null) {
            return null;
        }
        
        return UserTransfer.poToVo(userPO);
    }
    
    @Override
    public List<UserVO> getUserList(UserQueryVO queryVO) {
        // 1. 构建查询条件
        Specification<UserPO> spec = buildSpecification(queryVO);
        
        // 2. 构建分页和排序
        Pageable pageable = buildPageable(queryVO);
        
        // 3. 执行查询
        Page<UserPO> page = userDao.findAll(spec, pageable);
        
        // 4. 转换为VO
        List<UserVO> result = page.getContent().stream()
            .map(UserTransfer::poToVo)
            .collect(Collectors.toList());
        
        return result;
    }
    
    @Override
    public long getUserCount(UserQueryVO queryVO) {
        // 1. 构建查询条件
        Specification<UserPO> spec = buildSpecification(queryVO);
        
        // 2. 执行统计
        long count = userDao.count(spec);
        
        return count;
    }
    
    /**
     * 构建查询条件
     * 
     * @param queryVO 查询条件VO
     * @return Specification
     */
    private Specification<UserPO> buildSpecification(UserQueryVO queryVO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // 用户名模糊查询
            if (queryVO.getUsername() != null && !queryVO.getUsername().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    root.get("username"), 
                    "%" + queryVO.getUsername() + "%"));
            }
            
            // 真实姓名模糊查询
            if (queryVO.getRealName() != null && !queryVO.getRealName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    root.get("realName"), 
                    "%" + queryVO.getRealName() + "%"));
            }
            
            // 部门精确查询
            if (queryVO.getDepartment() != null && !queryVO.getDepartment().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                    root.get("department"), 
                    queryVO.getDepartment()));
            }
            
            // 角色精确查询
            if (queryVO.getRole() != null && !queryVO.getRole().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                    root.get("role"), 
                    queryVO.getRole()));
            }
            
            // 状态精确查询
            if (queryVO.getStatus() != null && !queryVO.getStatus().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                    root.get("status"), 
                    queryVO.getStatus()));
            }
            
            // 创建时间范围查询
            if (queryVO.getCreateTime() != null && queryVO.getCreateTime().length == 2) {
                try {
                    String startDateStr = queryVO.getCreateTime()[0];
                    String endDateStr = queryVO.getCreateTime()[1];
                    
                    if (startDateStr != null && !startDateStr.trim().isEmpty()) {
                        Date startDate = DATE_FORMAT.parse(startDateStr);
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("createTime"), 
                            startDate));
                    }
                    
                    if (endDateStr != null && !endDateStr.trim().isEmpty()) {
                        Date endDate = DATE_FORMAT.parse(endDateStr);
                        // 结束日期加1天，以包含当天的所有数据
                        endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000);
                        predicates.add(criteriaBuilder.lessThan(
                            root.get("createTime"), 
                            endDate));
                    }
                } catch (ParseException e) {
                    // 日期格式错误，忽略该条件
                }
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    /**
     * 构建分页和排序
     * 
     * @param queryVO 查询条件VO
     * @return Pageable
     */
    private Pageable buildPageable(UserQueryVO queryVO) {
        // 默认值
        int pageIndex = 0;
        int pageSize = 20;
        
        // 从查询条件中获取分页参数
        if (queryVO.getPageIndex() != null && queryVO.getPageIndex() > 0) {
            pageIndex = queryVO.getPageIndex() - 1; // JPA 的页码从0开始
        }
        
        if (queryVO.getPageSize() != null && queryVO.getPageSize() > 0) {
            pageSize = queryVO.getPageSize();
        }
        
        // 限制最大页面大小
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        
        // 按创建时间倒序排序
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        
        return PageRequest.of(pageIndex, pageSize, sort);
    }
}
