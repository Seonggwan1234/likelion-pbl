package Mission04.service;

import Mission04.domain.Lion;

import java.util.*;

public class LionManager {
    private final List<Lion> members = new ArrayList<>();

    public boolean addMember(Lion lion) {
        for (Lion m : members) {
            if (m.getName().equals(lion.getName())) {
                System.out.println("[중복] " + lion.getName() + " 은(는) 이미 등록된 멤버입니다.");
                return false;
            }
        }
        members.add(lion);
        System.out.println("[등록] " + lion.getName() + " 등록 완료.");
        return true;
    }

    public List<Lion> searchByName(String name) {
        List<Lion> result = new ArrayList<>();
        for (Lion m : members) {
            if (m.getName().contains(name)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Lion> filterByPart(String part) {
        List<Lion> result = new ArrayList<>();
        for (Lion m : members) {
            if (m.getPart().equals(part)) {
                result.add(m);
            }
        }
        return result;
    }

    public Map<String, List<Lion>> groupByPart() {
        Map<String, List<Lion>> partMap = new HashMap<>();
        for (Lion m : members) {
            partMap.computeIfAbsent(m.getPart(), k -> new ArrayList<>()).add(m);
        }
        return partMap;
    }

    public List<Lion> getAllMembers() {
        return Collections.unmodifiableList(members);
    }
}
