export interface LoginRequestDto {
    email: string,
    password: string
}

export interface LoginResponseDto {
    id: string,
    firstName: string,
    lastName: string,
    accessToken: string,
    refreshToken: string,
    roles: string[]
}

export interface RefreshRequestDto {
    accessToken: string,
    refreshToken: string
}

export interface RefreshResponseDto {
    accessToken: string,
    refreshToken: string
}

export interface User {
    firstName: string,
    lastName: string,
    accessToken: string,
    refreshToken: string,
    churchId?: string,
    roles: string[]
}

export interface RefreshRequestDto {
    accessToken: string,
    refreshToken: string
}

export interface SignInRequestDto {
    firstName: string,
    lastName: string,
    email: string,
    password: string
}

export interface CreateChurchRequestDto {
    name: string,
    cnpj: string
}

export interface CreateChurchResponseDto {
    id: string,
    name: string
}

export interface ReadChurchResponseDto {
    id: string,
    name: string,
    cnpj: string
}

export interface CreateRoleRequestDto {
    name: string
}

export interface CreateRoleResponseDto {
    id: string,
    name: string
}

export interface ReadRoleResponseDto {
    id: string,
    name: string
}

export interface CreateUserRequestDto {
    firstName: string,
    lastName: string,
    email: string,
    password: string
}

export interface CreateUserResponseDto {
    id: string,
    email: string
}

export interface ReadUserResponseDto {
    id: string,
    firstName: string,
    lastName: string,
    email: string
}

export interface AssociateUserAndRoleResponseDto {
    id: string,
    email: string,
    roles: string[]
}

export enum Gender {
    "MALE", "FEMALE"
}

export enum ReceptionMode {
    "PROFESSION_OF_FAITH", "PROFESSION_OF_FAITH_AND_BAPTISM", "LETTER_OF_TRANSFER", "JURISDICTION_ON_REQUEST", "EX_OFFICIO_JURISDICTION", "RESTORATION", "APPOINTMENT_OF_PRESBYTERY"
}

export enum ReligiousOrigin {
    "NONE", "CATHOLIC", "EVANGELICAL", "SPIRITUALIST", "OTHER"
}

export enum MaritalStatus {
    "SINGLE", "MARRIED", "DIVORCED", "SEPARATED", "WIDOWED"
}

export interface Member {
    firstName: string,
    lastName: string,
    birthDate: Date,
    placeBirth: string,
    gender: Gender,
    celebrantFullName: string,
    receptionDate: Date,
    receptionPlace: string,
    receptionMode: ReceptionMode,
    churchId: string
}

export const VOID_MEMBER: Member = {
    firstName: "",
    lastName: "",
    birthDate: new Date(),
    placeBirth: "",
    gender: Gender.MALE,
    celebrantFullName: "",
    receptionDate: new Date(),
    receptionPlace: "",
    receptionMode: ReceptionMode.PROFESSION_OF_FAITH,
    churchId: ""
}

export interface MemberCommunicantRequestDto extends Member {
    religiousOrigin: ReligiousOrigin,
    maritalStatus: MaritalStatus,
    profession: string,
    fullAddress: string,
    knowRead: boolean,
    knowWrite: boolean,
    childBaptized: boolean
}

export interface MemberNonCommunicantRequestDto extends Member {
    fatherFullName: string
    fatherProfessed: boolean,
    motherFullName: string,
    motherProfessed: boolean
}