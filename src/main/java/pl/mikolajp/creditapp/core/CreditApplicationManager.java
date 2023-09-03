package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.di.Inject;

import java.util.ArrayDeque;
import java.util.Deque;

public class CreditApplicationManager {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    @Inject
    private CreditApplicationService creditApplicationService;
    @Inject
    private CreditApplicationDecisionFactory creditApplicationDecisionFactory;
    private Deque<CreditApplication> queue = new ArrayDeque<>();

    public CreditApplicationManager(){}

    public void add(CreditApplication creditApplication) {
        log.info(String.format("Application %s is added to queue", creditApplication.getId()));
        queue.addFirst(creditApplication);
    }

    public void startProcessing() {
        while (!queue.isEmpty()) {
            CreditApplication creditApplication = queue.pollLast();
            log.info(String.format("Starting processing application with id=%s", creditApplication.getId()));
            CreditApplicationDecision decision = creditApplicationService.getDecision(creditApplication);
            log.info(creditApplicationDecisionFactory.getDecisionString(creditApplication, decision));
            MDC.remove("id");
        }

    }
}
