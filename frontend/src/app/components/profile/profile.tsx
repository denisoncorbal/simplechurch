'use client'
import { Dropdown } from '@mui/base/Dropdown';
import { Menu } from '@mui/base/Menu';
import { MenuButton as BaseMenuButton } from '@mui/base/MenuButton';
import { MenuItem as BaseMenuItem, menuItemClasses } from '@mui/base/MenuItem';
import { Person } from '@mui/icons-material';
import Divider from '@mui/material/Divider';
import Typography from '@mui/material/Typography';
import { blue } from '@mui/material/colors';
import { styled } from '@mui/system';
import { signOut, useSession } from 'next-auth/react';
import { usePathname, useRouter } from 'next/navigation';
import AnimatedListbox from './animatedListbox';

export default function Profile() {
  const router = useRouter();
  const path = usePathname();
  const { data: session } = useSession();

  const createHandleMenuClick = (menuItem: string) => {
    return () => {
      if (menuItem === 'logout') {
        signOut();
      }
    };
  };

  if (session && session.user && session.user.id)
    return (
      <Dropdown>
        <MenuButton sx={{
          display: 'flex',
          flexDirection: 'row'
        }}>
          <Person />
          <Typography>
            {session!.user.firstName + " " + session!.user.lastName}
          </Typography>
        </MenuButton>
        <Menu slots={{ listbox: AnimatedListbox }}>
          {session!.user.roles.map((roleName) => {
            return (
              <MenuItem key={roleName} onClick={createHandleMenuClick(roleName.trim().toLocaleLowerCase())}>{roleName}</MenuItem>
            )
          })}
          <Divider />
          <MenuItem onClick={createHandleMenuClick('logout')}>Log out</MenuItem>
        </Menu>
      </Dropdown>
    )
  if (session)
    signOut();

  if (path.includes("login"))
    return <></>

  router.push("/auth/login");
}

const MenuItem = styled(BaseMenuItem)(
  ({ theme }) => `
  list-style: none;
  padding: 8px;
  border-radius: 8px;
  cursor: default;
  user-select: none;

  &:last-of-type {
    border-bottom: none;
  }

  &:focus {
    outline: 3px solid ${theme.palette.mode === 'dark' ? blue[600] : blue[200]};
    background-color: ${theme.palette.mode === 'dark' ? theme.palette.grey[800] : theme.palette.grey[100]};
    color: ${theme.palette.mode === 'dark' ? theme.palette.grey[300] : theme.palette.grey[900]};
  }

  &.${menuItemClasses.disabled} {
    color: ${theme.palette.mode === 'dark' ? theme.palette.grey[700] : theme.palette.grey[400]};
  }
  `,
);

const MenuButton = styled(BaseMenuButton)(
  ({ theme }) => `
  font-family: 'IBM Plex Sans', sans-serif;
  font-weight: 600;
  font-size: 0.875rem;
  line-height: 1.5;
  padding: 8px 16px;
  border-radius: 8px;
  color: white;
  transition: all 150ms ease;
  cursor: pointer;
  background: ${theme.palette.mode === 'dark' ? theme.palette.grey[900] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? theme.palette.grey[700] : theme.palette.grey[200]};
  color: ${theme.palette.mode === 'dark' ? theme.palette.grey[200] : theme.palette.grey[900]};
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 0.05);

  &:hover {
    background: ${theme.palette.mode === 'dark' ? theme.palette.grey[800] : theme.palette.grey[50]};
    border-color: ${theme.palette.mode === 'dark' ? theme.palette.grey[600] : theme.palette.grey[300]};
  }

  &:active {
    background: ${theme.palette.mode === 'dark' ? theme.palette.grey[700] : theme.palette.grey[100]};
  }

  &:focus-visible {
    box-shadow: 0 0 0 4px ${theme.palette.mode === 'dark' ? blue[300] : blue[200]};
    outline: none;
  }
  `,
);
