package pd5;

import pd5.Model.ConsultantNote;
import pd5.Model.Email;
import pd5.Model.Sendable;
import pd5.Model.Sms;
import pd5.service.NotificationSender;

public class NotificationCenter {
    public static void main(String[] args) {
        Sms sms = new Sms(10,"     22   ", "26.10.2026", "123456789");
        Email email = new Email(22, "Witam dla jego", "11.01.2025", "mieszko1@o2.pl", "Tutaj nic nie ma");
        ConsultantNote consultantNote = new ConsultantNote(2398,"Kto mnie podpierdolil", "20.06.2024", "Kowalski");

        Sendable[] notifications = {email, sms};

        NotificationSender notificationSender = new NotificationSender();
        notificationSender.sendAll(notifications);

        consultantNote.editContent("cos tam ten");
        consultantNote.archive();
        consultantNote.editContent("cos tam ten dalej");

    }
}
