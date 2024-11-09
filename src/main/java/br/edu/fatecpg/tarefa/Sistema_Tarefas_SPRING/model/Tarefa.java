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
    private String status = "Pendente";

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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
}