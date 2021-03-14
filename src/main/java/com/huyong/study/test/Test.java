package com.huyong.study.test;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-14 9:06 下午
 */
public class Test {

    public static void main(String[] args) {
        String str = "bucketing_cols\n" +
                "cds\n" +
                "columns_v2\n" +
                "compaction_queue\n" +
                "completed_txn_components\n" +
                "database_params\n" +
                "db_privs\n" +
                "dbs\n" +
                "delegation_tokens\n" +
                "func_ru\n" +
                "funcs\n" +
                "global_privs\n" +
                "hive_locks\n" +
                "idxs\n" +
                "index_params\n" +
                "master_keys\n" +
                "metastore_db_properties\n" +
                "next_compaction_queue_id\n" +
                "next_lock_id\n" +
                "next_txn_id\n" +
                "notification_log\n" +
                "notification_sequence\n" +
                "nucleus_tables\n" +
                "part_col_privs\n" +
                "part_col_stats\n" +
                "part_privs\n" +
                "partition_events\n" +
                "partition_key_vals\n" +
                "partition_keys\n" +
                "partition_params\n" +
                "partitions\n" +
                "role_map\n" +
                "roles\n" +
                "sd_params\n" +
                "sds\n" +
                "sequence_table\n" +
                "serde_params\n" +
                "serdes\n" +
                "skewed_col_names\n" +
                "skewed_col_value_loc_map\n" +
                "skewed_string_list\n" +
                "skewed_string_list_values\n" +
                "skewed_values\n" +
                "sort_cols\n" +
                "tab_col_stats\n" +
                "table_params\n" +
                "tbl_col_privs\n" +
                "tbl_privs\n" +
                "tbls\n" +
                "txn_components\n" +
                "txns\n" +
                "type_fields\n" +
                "types\n" +
                "version";
        String[] split = str.split("\n");
        System.out.println(split.length);
        String str2 = "aux_table                     \n" +
                " bucketing_cols                \n" +
                " cds                           \n" +
                " columns_v2                    \n" +
                " compaction_queue              \n" +
                " completed_compactions         \n" +
                " completed_txn_components      \n" +
                " ctlgs                         \n" +
                " database_params               \n" +
                " db_privs                      \n" +
                " dbs                           \n" +
                " delegation_tokens             \n" +
                " func_ru                       \n" +
                " funds                         \n" +
                " global_privs                  \n" +
                " hive_locks                    \n" +
                " i_schema                      \n" +
                " idxs                          \n" +
                " index_params                  \n" +
                " key_constraints               \n" +
                " master_keys                   \n" +
                " materialization_rebuild_locks \n" +
                " metastore_db_properties       \n" +
                " min_history_level             \n" +
                " mv_creation_metadata          \n" +
                " mv_tables_used                \n" +
                " next_compaction_queue_id      \n" +
                " next_lock_id                  \n" +
                " next_txn_id                   \n" +
                " next_write_id                 \n" +
                " notification_log              \n" +
                " notification_sequence         \n" +
                " nucleus_tables                \n" +
                " part_col_privs                \n" +
                " part_col_stats                \n" +
                " part_privs                    \n" +
                " partition_events              \n" +
                " partition_key_vals            \n" +
                " partition_keys                \n" +
                " partition_params              \n" +
                " partitions                    \n" +
                " repl_txn_map                  \n" +
                " role_map                      \n" +
                " roles                         \n" +
                " runtime_stats                 \n" +
                " schema_version                \n" +
                " sd_params                     \n" +
                " sds                           \n" +
                " sequence_table                \n" +
                " serde_params                  \n" +
                " serdes                        \n" +
                " skewed_col_names              \n" +
                " skewed_col_value_loc_map      \n" +
                " skewed_string_list            \n" +
                " skewed_string_list_values     \n" +
                " skewed_values                 \n" +
                " sort_cols                     \n" +
                " tab_col_stats                 \n" +
                " table_params                  \n" +
                " tbl_col_privs                 \n" +
                " tbl_privs                     \n" +
                " tbls                          \n" +
                " txn_components                \n" +
                " txn_to_write_id               \n" +
                " txns                          \n" +
                " type_fields                   \n" +
                " types                         \n" +
                " version                       \n" +
                " wm_mapping                    \n" +
                " wm_pool                       \n" +
                " wm_pool_to_trigger            \n" +
                " wm_resourceplan               \n" +
                " wm_trigger                    \n" +
                " write_set";
        String[] split1 = str2.split("\n");
        System.out.println(split1.length);
        List<String> list = Arrays.stream(split).map(String::trim).collect(Collectors.toList());
        List<String> list1 = Arrays.stream(split1).map(String::trim).collect(Collectors.toList());
        System.out.println(list);
        List<String> collect = list1.stream().filter(e -> !list.contains(e)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect.size());
        List<String> result = list.stream().filter(e -> !list1.contains(e)).collect(Collectors.toList());
        System.out.println(result);

    }
}