package com.hubelias.fifatournament.domain.model.match;

import com.hubelias.fifatournament.DomainEventPublisher;
import com.hubelias.fifatournament.domain.model.team.TeamId;
import org.jetbrains.annotations.NotNull;

public class Match {
    @NotNull
    private final MatchId id;
    @NotNull
    private MatchState state = new Planned();
    @NotNull
    private final TeamId homeTeamId;
    @NotNull
    private final TeamId awayTeamId;
    @NotNull
    private Integer homeTeamGoals = 0;
    @NotNull
    private Integer awayTeamGoals = 0;

    Match(@NotNull MatchId id, @NotNull TeamId homeTeamId, @NotNull TeamId awayTeamId) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public void start() {
        state.start();
    }

    public void homeTeamScores() {
        state.homeTeamScores();
    }

    public void awayTeamScores() {
        state.awayTeamScores();
    }

    public void finalWhistle() {
        state.finalWhistle();
    }

    private interface MatchState {
        void start();

        void homeTeamScores();

        void awayTeamScores();

        void finalWhistle();
    }

    private class Planned implements MatchState {
        public void start() {
            state = new InProgress();
            DomainEventPublisher.getInstance().publish(new MatchStarted());
        }

        public void homeTeamScores() {
            throwMatchNotStarted();
        }

        public void awayTeamScores() {
            throwMatchNotStarted();
        }

        public void finalWhistle() {
            throwMatchNotStarted();
        }

        private void throwMatchNotStarted() {
            throw new IllegalStateException("Match has not started yet");
        }
    }

    private class InProgress implements MatchState {
        public void start() {
            throw new IllegalStateException("Match has already started");
        }

        public void homeTeamScores() {
            homeTeamGoals++;
        }

        public void awayTeamScores() {
            awayTeamGoals++;
        }

        public void finalWhistle() {
            state = new Ended();
            DomainEventPublisher.getInstance().publish(new MatchEnded());
        }
    }

    private class Ended implements MatchState {
        public void start() {
            throwMatchEnded();
        }

        public void homeTeamScores() {
            throwMatchEnded();
        }

        public void awayTeamScores() {
            throwMatchEnded();
        }

        public void finalWhistle() {
            throwMatchEnded();
        }

        private void throwMatchEnded() {
            throw new IllegalStateException("Match has ended");
        }
    }

    @NotNull MatchId getId() {
        return this.id;
    }
}
