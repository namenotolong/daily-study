package com.huyong.study.guawa;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Optional;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-08 2:46 下午
 */
public class Test {
    public static void main(String[] args) {
        ImmutableList<Integer> immutableList = ImmutableList.of(1,2,3,3,4);
        System.out.println(immutableList);
        Optional<Integer> of = Optional.of(1);
        Preconditions.checkArgument(true);
        boolean equal = Objects.equal(1, 2);
        boolean equals = java.util.Objects.equals(1, 2);
        int i = Objects.hashCode(new Test());
        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(Test.class);
        System.out.println(toStringHelper);
        toStringHelper.add("hello", "world");
        toStringHelper.add("null", "world");
        System.out.println(toStringHelper);
        System.out.println(Test.class.getSimpleName());
        System.out.println(Test.class.getName());
        List<Integer> list = Ordering.natural().sortedCopy(Lists.newArrayList(1, 345, 1, 3));
        System.out.println(list);
        Table.Cell<Integer, Integer, Integer> integerIntegerIntegerCell = Tables.immutableCell(1, 2, 3);
        System.out.println(integerIntegerIntegerCell.getColumnKey());
        ImmutableSet<Integer> of1 = ImmutableSet.of(1, 2, 3, 4);
        //Multimap<String, String> map = Multimaps.newMultimap();
    }
}

class Person implements Comparable<Person> {
    String name;
    Integer age;

    @Override
    public int compareTo(final Person that) {
        return ComparisonChain.start()
                .compare(this.name, that.name)
                .compare(this.age, that.age)
                .result();
    }
}
