import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';
import { ThemeProvider } from '@mui/material/styles';
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import Dashboard from '../components/dashboard/dashboard';
import theme from '../theme';
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "SimpleChurch",
  description: "Church management app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <AppRouterCacheProvider>
          <ThemeProvider theme={theme}>
            <Dashboard>
              {children}
            </Dashboard>
          </ThemeProvider>
        </AppRouterCacheProvider>
      </body>
    </html>
  );
}
