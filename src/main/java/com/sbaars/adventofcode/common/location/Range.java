package com.sbaars.adventofcode.common.location;

import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;

public class Range {
    private final Loc start;
    private final Loc end;

    public Range(Loc start, Loc end) {
        this.start = start;
        this.end = end;
    }

    public Stream<Loc> stream(){
        return rangeClosed(Math.min(start.intX(), end.intX()), Math.max(start.intX(), end.intX()))
                .boxed()
                .flatMap(x -> rangeClosed(Math.min(start.intY(), end.intY()), Math.max(start.intY(), end.intY())).mapToObj(y -> new Loc(x, y)));
    }
}
