import { Dropdown } from '@mui/base/Dropdown';
import { Menu } from '@mui/base/Menu';
import { MenuButton as BaseMenuButton } from '@mui/base/MenuButton';
import { MenuItem as BaseMenuItem, menuItemClasses } from '@mui/base/MenuItem';
import { Person } from '@mui/icons-material';
import Typography from '@mui/material/Typography';
import { blue } from '@mui/material/colors';
import { styled } from '@mui/system';
import AnimatedListbox from './animatedListbox';
interface MenuIntroductionProps {
  user: {
    firstName: string,
    lastName: string
  }
}
export default function MenuIntroduction(props: MenuIntroductionProps) {
  const createHandleMenuClick = (menuItem: string) => {
    return () => {
      console.log(`Clicked on ${menuItem}`);
    };
  };

  return (
    <Dropdown>
      <MenuButton sx={{
        display: 'flex',
        flexDirection: 'row'
      }}>
        <Person />
        <Typography>
          {props.user.firstName + ' ' + props.user.lastName}
        </Typography>
      </MenuButton>
      <Menu slots={{ listbox: AnimatedListbox }}>
        <MenuItem onClick={createHandleMenuClick('Profile')}>Profile</MenuItem>
        <MenuItem onClick={createHandleMenuClick('Language settings')}>
          Language settings
        </MenuItem>
        <MenuItem onClick={createHandleMenuClick('Log out')}>Log out</MenuItem>
      </Menu>
    </Dropdown>
  );
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
