package eu.twino.homework.blacklist;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BlackList {

    @Id
    private String personId;

    private String reason;

    public BlackList() {
        //for hibernate
    }

    public BlackList(String personId, String reason) {
        this.personId = personId;
        this.reason = reason;
    }

    public BlackList setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public BlackList setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(personId, blackList.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
