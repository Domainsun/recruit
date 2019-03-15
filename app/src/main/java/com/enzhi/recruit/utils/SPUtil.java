package com.enzhi.recruit.utils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.enzhi.recruit.bean.ResumeListBean;
import com.enzhi.recruit.bean.ResumesBean;
import com.enzhi.recruit.bean.UserListBean;
import com.enzhi.recruit.bean.UsersBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首选项工具
 */
public class SPUtil {


    /**
     * 设置用户信息
     *
     * @param userInfoBean
     */
    public static void setUserInfo(UsersBean userInfoBean) {
        String json = JsonUtils.objectToJson(userInfoBean);
        SPUtils.getInstance().put("userInfo", json);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UsersBean getUserInfo() {
        String json = SPUtils.getInstance().getString("userInfo");
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            return JsonUtils.jsonToObject(json, UsersBean.class);
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    public static List<UsersBean> getUserList() {
        String strUserList = SPUtils.getInstance().getString("userList");
        UserListBean userList = JsonUtils.jsonToObject(strUserList, UserListBean.class);
        if (userList != null) {
            return userList.getUsers();
        }
        return null;
    }

    /**
     * 设置用户列表
     */
    public static void setUserList(List<UsersBean> userList) {
        UserListBean userListBean = new UserListBean();
        userListBean.setUsers(userList);
        String json = JsonUtils.objectToJson(userListBean);
        SPUtils.getInstance().put("userList", json);
    }


    /**
     * 用户注册
     */
    public static void register(String account, String password, int userType) {
        List<UsersBean> userList = getUserList();
        UsersBean bean = new UsersBean();
        bean.setAccount(account);
        bean.setPassword(password);
        bean.setUserType(userType);
        if (userList != null && userList.size() > 0) {
            bean.setId(userList.size());
            userList.add(bean);
        } else {
            bean.setId(0);
            userList = new ArrayList<>();
            userList.add(bean);
        }
        setUserList(userList);
        ToastUtils.showShort("注册成功");
    }

    /**
     * 用户登录
     */
    public static UsersBean login(String account, String password) {
        List<UsersBean> userList = getUserList();
        if (userList != null && userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                UsersBean bean = userList.get(i);
                if (bean.getAccount().equals(account) && bean.getPassword().equals(password)) {
                    ToastUtils.showShort("登录成功");
                    return bean;
                }
            }
        }
        return null;
    }

    /**
     * 用户信息修改
     */
    public static void editUserInfo(UsersBean usersBean) {

        List<UsersBean> userList = getUserList();
        if (userList != null && userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                UsersBean bean = userList.get(i);
                if (usersBean.getId() == (bean.getId())) {
                    userList.set(i, usersBean);
                    setUserInfo(usersBean);
                    setUserList(userList);
                    ToastUtils.showShort("编辑成功");
                    break;
                }
            }
        }
    }


    /**
     * 设置 简历列表
     *
     * @param resumeListBean
     */
    public static void setResumes(ResumeListBean resumeListBean) {
        String json = JsonUtils.objectToJson(resumeListBean);
        SPUtils.getInstance().put("resumeList", json);
    }

    /**
     * 获取简历列表
     *
     * @param
     */
    public static ResumeListBean getResumes() {
        String resumeListStr = SPUtils.getInstance().getString("resumeList");

        ResumeListBean resumeListBean = JsonUtils.jsonToObject(resumeListStr, ResumeListBean.class);

        return resumeListBean;

    }

    /**
     * 新增简历
     */
    public static void addResume(ResumesBean resumesBean) {

        ResumeListBean resumeListBean = getResumes();
        if (resumeListBean != null && resumeListBean.getResumes() != null && resumeListBean.getResumes().size() > 0) {
            getResumes().getResumes().add(resumesBean);
            setResumes(resumeListBean);
        } else {
            ResumeListBean newResumeListBean = new ResumeListBean();
            List<ResumesBean> resumesBeanList = new ArrayList<>();
            resumesBeanList.add(resumesBean);
            newResumeListBean.setResumes(resumesBeanList);
            setResumes(newResumeListBean);
        }
    }


}
