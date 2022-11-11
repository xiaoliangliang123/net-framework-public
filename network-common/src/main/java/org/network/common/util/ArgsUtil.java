package org.network.common.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author alan.wang
 */
public class ArgsUtil {

    public static class RunArgs {

        Map<String,String> paramsMap ;


        public RunArgs(Map<String,String> paramsMap) {
            this.paramsMap = paramsMap;
        }

        public boolean hasPort(){
            return this.paramsMap.containsKey("port");
        }

        public Integer getPort(){
            return Integer.parseInt(this.paramsMap.get("port"));
        }

    }

    public static RunArgs init(String[] args){

        if(ArrayUtil.isNotEmpty(args)){

            Map paramsMap = new HashMap<>();
            for(int i = 0; i <args.length;i++){
              if(args[i].startsWith("--")){
                  paramsMap.put(StrUtil.subPre(args[0],2),args[1]);
              }
            }
            RunArgs runArgs = new RunArgs(paramsMap);
            return runArgs;
        }
        return new RunArgs(new HashMap<>());
    }

}
