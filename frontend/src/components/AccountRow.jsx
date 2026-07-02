import { CListGroupItem } from '@coreui/react'

const AccountRow = ({ accountNumber, balance }) => {
  return (
    <CListGroupItem className="d-flex justify-content-between align-items-center py-3">
      <div>
        <div className="fw-semibold">Account</div>
        <div className="fw-semibold">{accountNumber}</div>
      </div>
      <div className="fw-semibold">{balance}</div>
    </CListGroupItem>
  )
}

export default AccountRow
