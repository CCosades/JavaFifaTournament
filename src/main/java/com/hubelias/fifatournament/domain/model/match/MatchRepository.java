package com.hubelias.fifatournament.domain.model.match;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository {
    private List<Match> matches = new ArrayList<Match>();

    public void save(Match match) {
        matches.add(match);
    }

    //TODO: implement
    public Match findById(MatchId id) {
        return matches.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }
}
