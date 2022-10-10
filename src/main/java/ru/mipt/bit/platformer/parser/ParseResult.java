package ru.mipt.bit.platformer.parser;

import ru.mipt.bit.platformer.util.Transform;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseResult {
    private Map<Type, List<Transform>> value = new HashMap<>();

    public Transform addToList(Type key, Transform transform){
        if(!this.value.containsKey(key)){
            this.value.put(key, new ArrayList<Transform>());
        }

        this.value.get(key).add(transform);

        return transform;
    }

    public List<Transform> addList(Type key, List<Transform> list){
        if(this.value.containsKey(key)) return this.value.get(key);

        this.value.put(key, list);

        return this.value.get(key);
    }

    public List<Transform> getList(Type key){
        return this.value.get(key);
    }
}
