export interface Tarefa {
    usuario_id?: string | null;
    id: number;
    titulo: string;
    descricao: string;
    prioridade: number;
    status: string;
    responsavel: string;
}