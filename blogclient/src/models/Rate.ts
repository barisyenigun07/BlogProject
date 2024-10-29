import { User } from "./User";

export interface Rate {
    id: number,
    rateLevel: number,
    user: User
}