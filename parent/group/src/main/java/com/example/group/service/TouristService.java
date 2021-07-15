package com.example.group.service;

import com.example.group.pojo.GroupActivity;
import com.example.group.pojo.Tourist;
import com.example.group.pojo.User;

import java.util.List;

public interface TouristService {
	public GroupActivity findActivityWithTourists(Integer id);
	public List<GroupActivity> findAllActivityWithTourists();
	public void insertActivity(GroupActivity activity);
	public void insertTourist(Tourist tourist);
	public void updateTouristPay(Tourist tourist);
	public List<GroupActivity> findAllActivity();
	public void AddAAPay(int activity_id, double pay);
	public List<Tourist> findAllTourist();
	public Double findActivityBasicPay(Integer activity);
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
