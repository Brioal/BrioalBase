package com.brioal.brioalbase;

import java.io.Serializable;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/13.
 */

public class PersonBean implements Serializable {
    private String mName;
    private int mAge;
    private int mSex;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getSex() {
        return mSex;
    }

    public void setSex(int sex) {
        mSex = sex;
    }

    @Override
    public String toString() {
        return mName + "-" + mAge + "-" + mSex;
    }
}
