package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model;
import jakarta.persistence.*;

@Entity
@Table(name="Tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo_tarefa")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prioridade")
    private int prioridade;

    @Column(name = "status")
    private String status;

    @Column(name = "responsavel")
    private String responsavel;

    public Tarefa(String titulo, String descricao, int prioridade,  String responsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = "Pendente";
        this.responsavel = responsavel;
    }
    public Tarefa(){
    }

    @Override
    public String toString() {
        return "Tarefas{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", prioridade=" + prioridade +
                ", status='" + status + '\'' +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getStatus() {
        return status;
    }

    public String getResponsavel() {
        return responsavel;
    }
}