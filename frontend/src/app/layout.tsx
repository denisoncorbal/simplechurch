import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';
import { ThemeProvider } from '@mui/material/styles';
import type { Metadata } from "next";
import { Session } from 'next-auth';
import { Inter } from "next/font/google";
import SessionProviderWrapper from './api/auth/[...nextauth]/SessionProviderWrapper';
import Dashboard from './components/dashboard/dashboard';
import WrapLocalizationProvider from './context/localization/WrapLocalizationProvider';
import "./globals.css";
import theme from './theme';

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "SimpleChurch",
  description: "Church management app",
};

export default function RootLayout({
  children,
  session
}: Readonly<{
  children: React.ReactNode,
  session: Session
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <SessionProviderWrapper session={session}>
          <AppRouterCacheProvider>
            <ThemeProvider theme={theme}>
              <WrapLocalizationProvider>
                <Dashboard>
                  {children}
                </Dashboard>
              </WrapLocalizationProvider>
            </ThemeProvider>
          </AppRouterCacheProvider>
        </SessionProviderWrapper>
      </body>
    </html>
  );
}
