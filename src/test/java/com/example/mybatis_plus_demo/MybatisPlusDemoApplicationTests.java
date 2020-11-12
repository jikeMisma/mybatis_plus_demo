package com.example.mybatis_plus_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis_plus_demo.entity.User;
import com.example.mybatis_plus_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询user中的所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("测试插件");
        user.setAge(200);
        user.setEmail("2889479657@qq.com");

        //user.setCreateTime(new Date());
        //user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);
        System.out.println("insert"+insert);

    }

    //更新操作
    @Test
    public void updateById(){
        User user1 = new User();
        user1.setName("高雅函");
        user1.setAge(22);
        user1.setId(1L);
        int res = userMapper.updateById(user1);
        System.out.println(res);

    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1326793270365335553L);

        //进行修改
        user.setAge(200);
        userMapper.updateById(user);

    }

    //多个id的批量查询
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        users.forEach(System.out::println);
    }

    //通过map封装查询条件
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "gay豪111");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试selectPage分页
    @Test
    public void testSelectPage() {

        //创建page对象
        //传入两个参数：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1,5);
        //调用分页的方法
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);//每页数据集合
        System.out.println(page.getCurrent());//获取当前页
        System.out.println(page.getPages());//得到总页数
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//当前表中总记录数
        System.out.println(page.hasNext());//是否有下页
        System.out.println(page.hasPrevious());//是否有上页
    }

    //物理删除操作
    @Test
    public void testDeleteById(){

        int res = userMapper.deleteById(1326801914888744962L);
        System.out.println(res);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1326727491494088705L, 1326727491494088705L));
        System.out.println(result);
    }


    //mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
        //创建对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();


        //通过QueryWrapper设置条件和值

        //ge、gt、le、lt,大于，大于等于，小于，小于等于
        //查询大于等于30的记录
        //wrapper.ge("age",30);

        //eq、ne,等于，不等于
        //wrapper.eq("name","jack");
        //wrapper.ne("name","jack");


        //between、notBetween,介于XX之间
        //wrapper.between("age",18,24);

        //like,模糊查询
        //wrapper.like("name","豪");

        //orderByDesc,排序
        //wrapper.orderByDesc("id");
        wrapper.orderByAsc("id");

        //last,拼接
        //wrapper.last("limit 1");

        //查询指定的列
        wrapper.select("id","name")
                .between("age",18,24);


        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
        //System.out.println(users);
    }

}
