
package com.bway.two.utils.http;
import com.bway.two.view.IMview.customcallback.CallBack;

import java.util.Map;

/**
 * Created by WuXirui
 * Create Time: 2017/7/19
 * Description:
 */

public interface IHttpEngien {
    void get(String url, Map<String, Object> params, CallBack callBack);
    void post(String url, Map<String, Object> params, CallBack callBack);
}
