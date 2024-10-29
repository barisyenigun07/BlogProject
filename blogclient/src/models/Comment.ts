import { User } from "./User";

export interface Comment {
    id: number,
    content: string,
    publishedDate: string,
    user: User,
    parentCommentId: number
}