package com.huyong.study.guawa;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-08 5:59 下午
 */
public class COmmonUser {
    public static void main(String[] args) {
        String target = "[\n" +
                "    {\n" +
                "        key: 'husky-data-develop',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/husky-data-develop/#/temporary-query',\n" +
                "        name: '临时查询'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'husky-metis',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/husky-metis/#/real-time-develop',\n" +
                "        name: '实时开发'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'table-down-auth',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/table-down-auth/#/table-manage/table-list',\n" +
                "        name: '表管理'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'husky-ds-manager',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/husky-ds-manager/#/service-list',\n" +
                "        name: '数据服务'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'husky-data-sync',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/husky-data-sync/#/task-list',\n" +
                "        name: '数据同步'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'dqc',\n" +
                "        href: 'http://d.daily.vdian.net/dqc.html',\n" +
                "        name: '数据质量'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'husky-data-map',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/husky-data-map/#/job-relation',\n" +
                "        name: '数据地图'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'assets',\n" +
                "        href: 'http://d.daily.vdian.net/assets.html',\n" +
                "        name: '数据资产'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'jobops',\n" +
                "        href: 'http://d.daily.vdian.net/jobops.html',\n" +
                "        name: '任务运维'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'access',\n" +
                "        name: '权限管理',\n" +
                "        children: [\n" +
                "            {\n" +
                "                key: 'auth',\n" +
                "                href: 'http://d.daily.vdian.net/auth.html',\n" +
                "                name: '数据权限'\n" +
                "            },\n" +
                "            {\n" +
                "                key: 'mastiff',\n" +
                "                href: 'http://d.daily.vdian.net/mastiff.html',\n" +
                "                name: '功能权限'\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'measure',\n" +
                "        href: 'http://d.daily.vdian.net/measure.html',\n" +
                "        name: '指标中心'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'adminManager',\n" +
                "        href: 'http://d.daily.vdian.net/adminManager.html',\n" +
                "        name: '高级管理'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'esms',\n" +
                "        href: 'http://d.daily.vdian.net/esms/#/resource-management/index-resource',\n" +
                "        name: 'ES管理平台'\n" +
                "    },\n" +
                "    {\n" +
                "        key: 'billing-web',\n" +
                "        href: 'http://d.daily.vdian.net/bigdata/billing-web/#/settlement-system/bu-market',\n" +
                "        name: '计费系统'\n" +
                "    }\n" +
                "]";
        JSONArray array = JSONObject.parseArray(target);
        int count = 0;
        for (final Object o : array) {
            ((JSONObject)o).put("index", count++);
        }
        System.out.println(array.toString());
    }
}
