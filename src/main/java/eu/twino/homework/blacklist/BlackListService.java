package eu.twino.homework.blacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlackListService {

    private final BlackListRepository blackListRepository;

    @Autowired
    public BlackListService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }

    public void addPersonIdToBlackList(String personalId, String reason) {
        BlackList blackList = new BlackList(personalId, reason);
        blackListRepository.save(blackList);
    }

    public Optional<BlackList> getBlackList(String personalId) {
        return blackListRepository.findById(personalId);
    }
}
