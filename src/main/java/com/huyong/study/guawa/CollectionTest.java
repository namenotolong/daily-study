package com.huyong.study.guawa;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-08 7:08 下午
 */
public class CollectionTest {
    public static void multiSetTest() {
        HashMultiset<String> test = HashMultiset.create();
        test.add("hello");
        test.add("hello");
        test.add("hello");
        test.add("hello");
        test.add("hello");
        int hello = test.count("hello");
        System.out.println(hello);
        Set<Multiset.Entry<String>> strings = test.entrySet();
        System.out.println(strings);
    }

    public static void multiMapTest() {
        ListMultimap<String, String> build = MultimapBuilder.hashKeys().arrayListValues().build();
        build.put("a", "d");
        build.put("a", "d");
        build.put("a", "da");
        System.out.println(build);
    }

    public static void testBiMap() {
        HashBiMap<String, Integer> test = HashBiMap.create();
        test.put("zhangsan", 12);
        test.put("lisi", 20);
        System.out.println(test.get("zhangsan"));
        System.out.println(test.inverse().get(12));
    }

    public static void testTable() {
        HashBasedTable<Object, Object, Object> test = HashBasedTable.create();
        test.put(1,1,1);
        test.put(2,1,2);
        test.put(3,1,3);
        System.out.println(test.get(1,1));
    }

    public static void testRange() {
        Range<Integer> closed = Range.closed(1, 10);
        System.out.println(closed);
    }

    public static void testCollection() {
    }

    public static void testSplitter() {
        String var1 = "1,2,3,4,,,4";
        Iterable<String> split = Splitter.on(",").split(var1);
        for (final String s : split) {
            System.out.println(s);
        }
    }

    public static void testJoiner() {
        String join = Joiner.on(":").join(Lists.newArrayList(1, 2, 3, 4, 5, 6));
        System.out.println(join);
    }

    public static void testCharMatcher() {
        CharMatcher.ascii();
    }

    public static void testCaseFormat() {
        String test = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, "sdfSdf_af");
        System.out.println(test);
    }

    public static void testPrimitive() {
    }

    public static void testReflection() {
        String packageName = Reflection.getPackageName(Test.class);
        System.out.println(packageName);
    }

    public static void testCache() {
        LoadingCache<Integer, String> build = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .removalListener((RemovalListener) notification -> System.out.println(11212))
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(final Integer key) throws Exception {
                        return "nullijkn";
                    }
                });
        build.put(1, "hello");
        build.put(2, "ss");
        build.invalidate(1);
        System.out.println(build.getIfPresent(1));
        try {
            System.out.println(build.get(122, () -> "hello world"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void testTypeToken() {
        TypeToken<String> test = TypeToken.of(String.class);

    }

    public static void testClassPath() {
        ClassPath from = null;
        try {
            from = ClassPath.from(Test.class.getClassLoader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(from);
        for (final ClassPath.ClassInfo allClass : from.getTopLevelClasses("com.huyong.stuty")) {
            System.out.println(allClass);
        }
    }

    public static void main(String[] args) {
        testClassPath();
    }
}
