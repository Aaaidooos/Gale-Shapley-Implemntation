package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person
{
    boolean is_engaged;
    String name;
    List<Person> preferred;
    List<Person> list_of_men = new LinkedList<>(); // for womens
    Person lover;
    int state;

    public List<Person> generate_preferred(List<Person>preferred)
    {
        List<Integer> visited = new LinkedList<>();
        List<Person>list_preferred = new LinkedList<>();
        Random rand = new Random();

        while (visited.size() != preferred.size())
        {
            int index = rand.nextInt(preferred.size());
            if(!visited.contains(index))
            {
                list_preferred.add(preferred.get(index));
                visited.add(index);
            }
        }

        visited.clear();
        return list_preferred;
    }

    public static Person init(String name, int state)
    {
        Person person = new Person();
        person.name = name;
        person.is_engaged = false;
        person.state = state;
        person.preferred = null;
        person.lover = null;
        return person;
    }

    public void add_man_to_list(Person man)
    {
        list_of_men.add(man);
    }

    public String toString()
    {
        return name + " ";
    }

    public void do_propose(Person women)
    {
        if(women.lover == null)
        {
            women.lover = this;
            women.is_engaged = true;
            this.lover = women;
            this.is_engaged = true;
        }
        else{
                if(women.preferred.indexOf(women.lover) > women.preferred.indexOf(this))
                {
                    women.lover.is_engaged = false;
                    women.lover.lover = null;
                    women.lover = this;
                    women.is_engaged = true;
                    this.lover = women;
                    this.is_engaged = true;
                }
            }
    }
}
