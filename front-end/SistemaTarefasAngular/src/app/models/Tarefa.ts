export interface Tarefa {
    usuario_id?: string | null;
    id: number;
    titulo_tarefa: string;
    descricao: string;
    prioridade: number;
    status: string;
    responsavel: string;
}