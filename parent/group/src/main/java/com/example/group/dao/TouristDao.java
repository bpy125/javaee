package com.example.group.dao;

import com.example.group.pojo.GroupActivity;
import com.example.group.pojo.Tourist;
import com.example.group.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Customer接口文件
 */
@Mapper   //告诉springboot 这是一个mybatis的mapper类
@Repository
public interface TouristDao {
	/**
	 * 根据id查询客户信息
	 */
	public GroupActivity findActivityWithTourists(Integer id);
	public List<GroupActivity> findAllActivityWithTourists();
	public List<GroupActivity> findAllActivity();
	public void insertActivity(GroupActivity activity);
	public void insertTourist(Tourist tourist);
	public void updateTouristPay(Tourist tourist);
	public void AddAAPay(int activity_id, double pay);
	public List<Tourist> findAllTourist();
	public Double findActivityBasicPay(Integer activity_id);
	public void addUser(User user);
	public User findUser(String name);
	public void updateActivityNumber(Integer id);
	public List<GroupActivity> findActivityByLeader(String leader);
	public void AddAAPay1(int activity_id,double pay);
	public Tourist findTourist(int id,int activity_id);
	public List<GroupActivity> findActivityByName(String name);
	public GroupActivity findActivityByName1(String name);
	public List<Tourist> findTouristByName(String name);
	public GroupActivity findActivityById(Integer activity_id);

}
