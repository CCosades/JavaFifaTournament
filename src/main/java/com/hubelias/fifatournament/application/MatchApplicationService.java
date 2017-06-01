package com.hubelias.fifatournament.application;

import com.hubelias.fifatournament.domain.model.match.Match;
import com.hubelias.fifatournament.domain.model.match.MatchId;
import com.hubelias.fifatournament.domain.model.match.MatchRepository;

public class MatchApplicationService {
    private final MatchRepository matchRepository;

    MatchApplicationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    void startMatch(MatchId id) {
        Match match = matchRepository.findById(id);
        match.start();
        matchRepository.save(match);
    }

    void endMatch(MatchId id) {
        Match match = matchRepository.findById(id);
        match.finalWhistle();
        matchRepository.save(match);
    }
}
