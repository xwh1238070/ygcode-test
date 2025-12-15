package com.ygsoft.user.management.controller;

import com.ygsoft.jt.teng.fw.core.base.model.CommonResult;
import com.ygsoft.user.management.domain.vo.DepartmentBatchVO;
import com.ygsoft.user.management.domain.vo.DepartmentQueryVO;
import com.ygsoft.user.management.domain.vo.DepartmentTreeVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;
import com.ygsoft.user.management.service.application.DepartmentService;
import com.ygsoft.user.management.service.query.DepartmentQueryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 部门管理控制器
 * 
 * 职责：
 * 1. 接收和验证HTTP请求参数
 * 2. 调用Service层处理业务逻辑
 * 3. 返回统一格式的响应结果
 * 
 * @author developer
 * @date 2024-12-15
 */
@RestController
@Api(tags = "部门管理", description = "部门管理相关接口")
@RequestMapping({"{securitydomain}/{vipaddress}/{读写分离标识}/model/department/"})
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private DepartmentQueryService departmentQueryService;
    
    /**
     * 查询部门列表
     */
    @ApiOperation(value = "查询部门列表", notes = "分页查询部门列表，支持多条件组合查询",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/list"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryVO", 
            value = "{\"deptName\": \"部门名称\", \"deptCode\": \"部门编码\", \"parentId\": \"父部门ID\", \"status\": \"状态\", \"deptLevel\": 层级, \"pageSize\": 20, \"pageIndex\": 1}", 
            paramType = "body", dataType = "Object", required = false) })
    @PostMapping(path = "/list")
    public CommonResult<List<DepartmentVO>> list(@RequestBody(required = false) final DepartmentQueryVO queryVO) {
        final DepartmentQueryVO query = queryVO != null ? queryVO : new DepartmentQueryVO();
        final List<DepartmentVO> departmentList = departmentQueryService.getDepartmentList(query);
        return CommonResult.of(departmentList);
    }
    
    /**
     * 查询部门树形结构
     */
    @ApiOperation(value = "查询部门树形结构", notes = "获取完整的部门树形结构",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/tree"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", 
            value = "{\"status\": \"状态\"}", 
            paramType = "body", dataType = "Object", required = false) })
    @PostMapping(path = "/tree")
    public CommonResult<List<DepartmentTreeVO>> tree(@RequestBody(required = false) final Map<String, String> request) {
        final String status = request != null ? request.get("status") : null;
        final List<DepartmentTreeVO> tree = departmentQueryService.getDepartmentTree(status);
        return CommonResult.of(tree);
    }
    
    /**
     * 获取部门总数
     */
    @ApiOperation(value = "获取部门总数", notes = "获取符合查询条件的部门总数",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/count"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryVO", 
            value = "{\"deptName\": \"部门名称\", \"deptCode\": \"部门编码\", \"status\": \"状态\"}", 
            paramType = "body", dataType = "Object", required = false) })
    @PostMapping(path = "/count")
    public CommonResult<Long> count(@RequestBody(required = false) final DepartmentQueryVO queryVO) {
        final DepartmentQueryVO query = queryVO != null ? queryVO : new DepartmentQueryVO();
        final long count = departmentQueryService.getDepartmentCount(query);
        return CommonResult.of(count);
    }
    
    /**
     * 获取部门详情
     */
    @ApiOperation(value = "获取部门详情", notes = "根据部门ID查询部门详细信息",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/detail"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", 
            value = "{\"deptId\": \"部门ID\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/detail")
    public CommonResult<DepartmentVO> detail(@RequestBody final Map<String, String> request) {
        final String deptId = request.get("deptId");
        final DepartmentVO department = departmentQueryService.getDepartmentById(deptId);
        return CommonResult.of(department);
    }
    
    /**
     * 新增部门
     */
    @ApiOperation(value = "新增部门", notes = "创建新部门",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/add"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "departmentVO", 
            value = "{\"deptCode\": \"部门编码\", \"deptName\": \"部门名称\", \"parentId\": \"父部门ID\", \"sortOrder\": 排序号, \"leader\": \"负责人\", \"phone\": \"电话\", \"email\": \"邮箱\", \"description\": \"描述\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/add")
    public CommonResult<String> add(@RequestBody(required = true) final DepartmentVO departmentVO) {
        final String deptId = departmentService.createDepartment(departmentVO);
        return CommonResult.of(deptId);
    }
    
    /**
     * 编辑部门
     */
    @ApiOperation(value = "编辑部门", notes = "更新部门信息",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/edit"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "departmentVO", 
            value = "{\"deptId\": \"部门ID\", \"deptName\": \"部门名称\", \"sortOrder\": 排序号, \"leader\": \"负责人\", \"phone\": \"电话\", \"email\": \"邮箱\", \"description\": \"描述\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/edit")
    public CommonResult<Boolean> edit(@RequestBody(required = true) final DepartmentVO departmentVO) {
        final boolean success = departmentService.updateDepartment(departmentVO);
        return CommonResult.of(success);
    }
    
    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "删除部门，支持批量删除",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/delete"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "batchVO", 
            value = "{\"deptIds\": [\"部门ID1\", \"部门ID2\"]}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/delete")
    public CommonResult<Integer> delete(@RequestBody(required = true) final DepartmentBatchVO batchVO) {
        final int count = departmentService.deleteDepartments(batchVO);
        return CommonResult.of(count);
    }
    
    /**
     * 启用部门
     */
    @ApiOperation(value = "启用部门", notes = "启用部门，支持批量操作",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/enable"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "batchVO", 
            value = "{\"deptIds\": [\"部门ID1\", \"部门ID2\"]}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/enable")
    public CommonResult<Integer> enable(@RequestBody(required = true) final DepartmentBatchVO batchVO) {
        final int count = departmentService.enableDepartments(batchVO);
        return CommonResult.of(count);
    }
    
    /**
     * 禁用部门
     */
    @ApiOperation(value = "禁用部门", notes = "禁用部门，支持批量操作",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/disable"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "batchVO", 
            value = "{\"deptIds\": [\"部门ID1\", \"部门ID2\"]}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/disable")
    public CommonResult<Integer> disable(@RequestBody(required = true) final DepartmentBatchVO batchVO) {
        final int count = departmentService.disableDepartments(batchVO);
        return CommonResult.of(count);
    }
    
    /**
     * 查询子部门
     */
    @ApiOperation(value = "查询子部门", notes = "查询指定部门的直接子部门",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/children"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", 
            value = "{\"parentId\": \"父部门ID\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/children")
    public CommonResult<List<DepartmentVO>> children(@RequestBody final Map<String, String> request) {
        final String parentId = request.get("parentId");
        final List<DepartmentVO> children = departmentQueryService.getChildDepartments(parentId);
        return CommonResult.of(children);
    }
    
    /**
     * 统计部门用户数量
     */
    @ApiOperation(value = "统计部门用户数量", notes = "统计指定部门的用户数量",
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/department/userCount"),
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", 
            value = "{\"deptId\": \"部门ID\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @PostMapping(path = "/userCount")
    public CommonResult<Integer> userCount(@RequestBody final Map<String, String> request) {
        final String deptId = request.get("deptId");
        final int count = departmentQueryService.getDepartmentUserCount(deptId);
        return CommonResult.of(count);
    }
}
