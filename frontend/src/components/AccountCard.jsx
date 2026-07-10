import { CButton } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilCreditCard } from '@coreui/icons'

const AccountCard = ({
  accountName = 'Account',
  accountNumber,
  balance,
  balanceLabel,
  icon,
  color = 'primary',
}) => {
  const maskedAccountNumber = accountNumber ? `•••• ${String(accountNumber).slice(-4)}` : '••••'

  return (
    <CButton color="dark" variant="outline" className="account-card">
      <span className={`account-card-icon text-${color}`}>
        <CIcon icon={icon || cilCreditCard} size="lg" />
      </span>

      <span className="account-card-info">
        <span className="account-card-name">{accountName}</span>
        <span className="account-card-number">{maskedAccountNumber}</span>
      </span>

      <span className="account-card-balance-group">
        <span className="account-card-balance">{balance}</span>
        {balanceLabel && <span className="account-card-balance-label">{balanceLabel}</span>}
      </span>
    </CButton>
  )
}

export default AccountCard
