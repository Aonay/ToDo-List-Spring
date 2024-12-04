package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void enviarEmail(String destinatario, String nome) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destinatario);
        mensagem.setSubject("Boas vindas ao IntelliTask");
        mensagem.setText("Olá, "+ nome +"! \uD83C\uDF89 \n " +
                "Bem-vindo(a) ao IntelliTask, o sistema mais inteligente para gerenciar suas tarefas e, claro, salvar o seu precioso tempo! ⏰✨\n" +
                "\n" +
                "Estamos super empolgados por você ter dado esse passo rumo a uma vida mais organizada e produtiva. Aqui no IntelliTask, sua lista de afazeres se transforma em uma verdadeira central de eficiência e simplicidade.\n" +
                "\n" +
                "\uD83D\uDCA1 Benefícios que você vai amar:\n" +
                "\n" +
                "Organização sem complicação: Crie, edite e acompanhe suas tarefas de forma rápida e fácil.\n" +
                "Tudo no seu ritmo: Personalize seus prazos e receba lembretes amigáveis (sem broncas, prometemos! \uD83D\uDE05).\n" +
                "Produtividade com inteligência: Nosso sistema aprende com você para sugerir formas de otimizar sua rotina.\n" +
                "Relatórios incríveis: Visualize seu progresso e veja como você está arrasando nas suas metas! \uD83C\uDFAF\n" +
                "Mal podemos esperar para ver você dominando suas tarefas como um(a) verdadeiro(a) mestre da organização. Se precisar de ajuda ou quiser compartilhar suas conquistas, estamos a um clique de distância. \uD83D\uDE0A\n" +
                "\n" +
                "Agora, bora fazer suas tarefas trabalharem para você! \uD83D\uDE80\n" +
                "\n" +
                "Abraços da equipe IntelliTask \uD83D\uDCBB");
        mensagem.setFrom("rigonigabriel12@gmail.com");
        mailSender.send(mensagem);
    }
}