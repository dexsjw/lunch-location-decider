export interface LunchSessionRequest {
    roomId: string,
    ownerCode: string,
    activeStatus: boolean,
    restaurant: string | null
}

export interface LunchSessionResponse {
    roomId: string,
    hasOwnerCode: boolean,
    activeStatus: boolean,
    restaurants: string | null,
    message: string | null
}