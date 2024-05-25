import Typography from '@mui/material/Typography';

export default function Copyright(props: any) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © Denison Gonçalves Corbal '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}