package own.jdk.multiThreadPattern.chapter8.activeObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Recipient implements Serializable {

    private static final long serialVersionUID = -5427696559429827584L;

    private Set<String> to = new HashSet<>();

    public void addTo(String msisdn) {
        to.add(msisdn);
    }

    public Set<String> getToList() {
        return (Set<String>) Collections.unmodifiableCollection(to);
    }

}
