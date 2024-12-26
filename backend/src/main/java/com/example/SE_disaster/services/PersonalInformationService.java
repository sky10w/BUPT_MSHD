package com.example.SE_disaster.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.PersonalInformationMapper;
import com.example.SE_disaster.mappers.UserActivitiesMapper;
import com.example.SE_disaster.mappers.UserMapper;
import com.example.SE_disaster.models.PersonalInformation;
import com.example.SE_disaster.models.User;
import com.example.SE_disaster.models.UserActivities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInformationService
{
    @Autowired
    PersonalInformationMapper personalInformationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserActivitiesMapper userActivitiesMapper;
    
    public boolean insertPersonalInformation(Long uid,String phone_number,String email,String nick_name)
    {
        if(personalInformationMapper.selectById(uid) != null)
        {
            return false;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.uid = uid;
        personalInformation.phonenumber = phone_number;
        personalInformation.email = email;
        personalInformation.nickname = nick_name;
        personalInformationMapper.insert(personalInformation);
        return true;
    }
    
    public boolean updatePersonalInformation(Long uid,String phone_number,String email,String nick_name)
    {
        if(personalInformationMapper.selectById(uid) == null)
        {
            return false;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.uid = uid;
        if(phone_number != null && !phone_number.isEmpty())
        {
            personalInformation.phonenumber = phone_number;
        }
        if(email != null && !email.isEmpty())
        {
            personalInformation.email = email;
        }
        if(nick_name != null && !nick_name.isEmpty())
        {
            personalInformation.nickname = nick_name;
        }
        personalInformationMapper.updateById(personalInformation);
        return true;
    }
    
    public PersonalInformation getPersonalInformation(Long uid)
    {
        return personalInformationMapper.selectById(uid);
    }
    
    public boolean changePassword(Long uid,String password)
    {
        User user = userMapper.selectById(uid);
        if(user == null)
        {
            return false;
        }
        user.setPassword(password);
        userMapper.updateById(user);
        return true;
    }
    
    public String getActivities(Long uid)
    {
        QueryWrapper<UserActivities> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        var res =  userActivitiesMapper.selectList(wrapper);
        res.sort((a,b)->b.date.compareTo(a.date));
        List<String> activities = new ArrayList<>();
        for(UserActivities userActivities:res)
        {
            activities.add(userActivities.date.toString()+" "+userActivities.activity);
        }
        return String.join(",",activities);
    }
    
    public void insertActivities(Long uid, LocalDate date,String activity)
    {
        UserActivities userActivities = new UserActivities();
        userActivities.setUid(uid);
        userActivities.setDate(date);
        userActivities.setActivity(activity);
        userActivitiesMapper.insert(userActivities);
    }
}
