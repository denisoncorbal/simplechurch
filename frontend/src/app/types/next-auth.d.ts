import { DefaultSession } from "next-auth"
import { LoginResponseDto } from "../interfaces/interfaces"

declare module "next-auth" {
  /**
   * Returned by `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context
   */
  interface Session {
    user: LoginResponseDto & DefaultSession["user"]
  }

}

declare module 'next-auth/jwt' {
  interface JWT {
    user: LoginResponseDto & DefaultSession["user"]
  }
}
