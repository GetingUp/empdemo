package com.neuedu.mapper;

import com.neuedu.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {


    /**
     * 查询所有emp
     * @return
     */
    List<Emp> listEmp();

    /**
     * 查询emp表中的数据个数
     * @return
     */
    int getCount();


    /**
     * 根据id查询emp
     * @param id
     * @return
     */
    Emp getEmpById(int id);
    /**
     * 根据id的数组来删除多个emp
     * @param ids
     * @return
     */
    int deleteEmpByIds(int[] ids);

    /**
     * 根据部门的id来删除员工
     * @param dept_ids
     * @return影响行数
     */
    int deleteEmpByDeptId(int[] dept_ids);

    /**
     * 添加Emp
     * @param emp
     * @return
     */
    int saveEmp(@Param("emp") Emp emp);

    /**
     * 修改enp
     * @param emp
     * @return
     */
    int updateEmp(@Param("emp") Emp emp);
}
