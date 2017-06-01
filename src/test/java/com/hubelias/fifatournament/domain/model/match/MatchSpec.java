package com.hubelias.fifatournament.domain.model.match;

import com.hubelias.fifatournament.domain.model.team.TeamId;
import org.junit.Test;

public class MatchSpec {
    private Match newInstance() {
        return new Match(new MatchId(), new TeamId(), new TeamId());
    }

    private void expectNoEvents() {}

    @Test
    public void defaultUsage() {
        Match match = newInstance();
        match.start();
        match.homeTeamScores();
        match.awayTeamScores();
        match.awayTeamScores();
        match.finalWhistle();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotHomeScoreIfNotStarted() {
        newInstance().homeTeamScores();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAwayScoreIfNotStarted() {
        newInstance().awayTeamScores();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotEndIfNotStarted() {
        newInstance().finalWhistle();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartIfStarted() {
        Match match = newInstance();
        match.start();
        match.start();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartIfEnded() {
        matchEndedInstance().start();
        expectNoEvents();
    }

    private Match matchEndedInstance() {
        Match match = newInstance();
        match.start();
        match.finalWhistle();
        return match;
    }

    @Test(expected = IllegalStateException.class)
    public void cannotHomeScoreIfEnded() {
        matchEndedInstance().homeTeamScores();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAwayScoreIfEnded() {
        matchEndedInstance().awayTeamScores();
        expectNoEvents();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotEndIfEnded() {
        matchEndedInstance().finalWhistle();
        expectNoEvents();
    }
}
