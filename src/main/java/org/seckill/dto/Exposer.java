package org.seckill.dto;

public class Exposer {

    private final boolean exposed;

    private final String md5;

    private final long seckillId;

    private final long now;

    private final long start;

    private final long end;

    public static class Builder {
        private final boolean exposed;
        private final long seckillId;

        // optional arguments
        private String  md5     = "";
        private long    now     = 0;
        private long    start   = 0;
        private long    end     = 0;

        public Builder (boolean exposed, long seckillId) {
            this.exposed    = exposed;
            this.seckillId  = seckillId;
        }

        public Builder md5(String md5) {
            this.md5 = md5;
            return this;
        }

        public Builder now(long now) {
            this.now = now;
            return this;
        }
        public Builder start(long start) {
            this.start = start;
            return this;
        }
        public Builder end(long end) {
            this.end = end;
            return this;
        }

        public Exposer build() {
            return new Exposer(this);
        }

    }

    private Exposer(Builder builder) {
        this.exposed    = builder.exposed;
        this.md5        = builder.md5;
        this.seckillId  = builder.seckillId;
        this.now        = builder.now;
        this.start      = builder.start;
        this.end        = builder.end;
    }
}
