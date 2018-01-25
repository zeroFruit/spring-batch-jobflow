package io.spring.demoapp.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JobListener implements JobExecutionListener {
    private JavaMailSender mailSender;

    public JobListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail =
                getSimpleMailMessage(String.format("%s is starting", jobName),
                        String.format("Per your request, we are informing you that %s is starting", jobName));
        mailSender.send(mail);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }

    private SimpleMailMessage getSimpleMailMessage(String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("14wnrkim@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        return mail;
    }
}
