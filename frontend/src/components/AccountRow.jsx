import { CListGroupItem } from '@coreui/react'

const AccountRow = ({ name, number, balance }) => {
  return (
    <CListGroupItem className="d-flex justify-content-between align-items-center py-3">
      <div>
        <div className="fw-semibold">{name}</div>
        <div className="fw-semibold">{number}</div>
      </div>
      <div className="fw-semibold">{balance}</div>
    </CListGroupItem>
  )
}

export default AccountRow
