export interface LunchSession {
    id: number,
    ownerCode: string,
    roomCode: string,
    activeStatus: boolean,
    restaurants: string | null,
    restaurantsList: string[] | null
}