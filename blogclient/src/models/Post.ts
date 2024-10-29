import { Comment } from "./Comment"
import { Tag } from "./Tag"
import { User } from "./User"

export interface Post {
    id: number,
    title: string,
    description: string,
    captionPhotoLink: string
    content: string,
    postType: string,
    publishedDate: string,
    modifiedDate: string,
    averageRate: number,
    tags: Tag[],
    comments: Comment[],
    user: User
}