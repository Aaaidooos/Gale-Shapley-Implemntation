package com.company;


import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Runner
{
    public static List<String> generate_preferred(String[]preferred)
    {
        List<Integer> visited = new LinkedList<>();
        List<String>list_preferred = new LinkedList<>();
        Random rand = new Random();

        while (visited.size() != preferred.length)
        {
            int index = rand.nextInt(preferred.length);
            if(!visited.contains(index))
            {
                list_preferred.add(preferred[index]);
                visited.add(index);
            }
        }

        visited.clear();
        return list_preferred;
    }

    public static void add_to_list(List<Person>list, int state, String[]names)
    {
        for (String name : names) {
            Person person = Person.init(name, state);
            list.add(person);
        }
        state += 8;

    }

    public static void generate_list(List<Person>list, List<Person>list_to_generate)
    {
        for(Person man : list)
        {
            man.preferred = man.generate_preferred(list_to_generate);
        }
    }
    public static void run_krug(List<Person>men_list, int i)
    {
        for(Person man : men_list)
        {
            if(!man.is_engaged)
            {
                System.out.println(man.name + " does propose to " + man.preferred.get(i));
                man.do_propose(man.preferred.get(i));
                man.preferred.get(i).add_man_to_list(man);
            }
        }

        System.out.println("**********************************************");

        for(Person man : men_list)
        {
            if(man.is_engaged)
            {
                System.out.println(man.name + " + " + man.lover);
            }
        }
        System.out.println("**********************************************");
    }
    public static void main(String[] args)
    {

        String[]men = {"James", "Oscar", "Harry", "Alexander"};
        String[]women = {"Elizabeth", "Linda", "Ann", "Rouse"};

        List<Person> men_list = new LinkedList<>();
        List<Person> women_list = new LinkedList<>();

        add_to_list(men_list, 1, men);
        add_to_list(women_list, 0, women);

        generate_list(men_list, women_list);
        generate_list(women_list, men_list);
        System.out.println("quick info");
        for(Person woman : women_list)
        {
            System.out.println(woman + " loves " + woman.preferred);
        }

        run_krug(men_list, 0);
        run_krug(men_list, 1);
        run_krug(men_list, 2);
        run_krug(men_list, 3);

        System.out.println();
        System.out.println("Final result: ");
        for(Person man : men_list)
        {
            System.out.println(man + " + " + man.lover);
        }

    }
}
