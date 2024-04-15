package org.example.services;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {

    @Value("#{'${api.keys}'.split(',')}")
    private List<String> allowedApiKeys;

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public boolean isValidApiKey(String apiKey) {
        return allowedApiKeys.contains(apiKey);
    }

    public boolean allowRequest(String apiKey) {
        Bucket bucket = cache.computeIfAbsent(apiKey, this::createBucket);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        return probe.isConsumed();
    }

    private Bucket createBucket(String apiKey) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(2, Refill.intervally(1, Duration.ofSeconds(2))))
                .build();
    }
}
