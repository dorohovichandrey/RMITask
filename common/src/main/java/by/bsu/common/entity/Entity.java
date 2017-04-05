package by.bsu.common.entity;

import java.io.Serializable;

/**
 * Created by User on 21.03.2017.
 */
public abstract class Entity<K> implements Serializable {
    K id;

    public Entity(K id) {
        this.id = id;
    }

    public Entity() {
    }

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity<?> entity = (Entity<?>) o;

        return id.equals(entity.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
