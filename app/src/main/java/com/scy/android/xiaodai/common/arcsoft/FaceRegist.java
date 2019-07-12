package com.scy.android.xiaodai.common.arcsoft;

import com.arcsoft.facerecognition.AFR_FSDKFace;

import java.util.ArrayList;
import java.util.List;

/**
 * created by scy on 2019/7/2 19:25
 * gmail：cherseey@gmail.com
 */
public class FaceRegist {

    String mName;
    List<AFR_FSDKFace> mFaceList;

    public FaceRegist(String name) {
        mName = name;
        mFaceList = new ArrayList<>();
    }
}
